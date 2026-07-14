package com.adithya.watertracker.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.adithya.watertracker.dto.response.CompareResponse;
import com.adithya.watertracker.dto.request.WaterRequest;
import com.adithya.watertracker.entity.User;
import com.adithya.watertracker.entity.WaterIntake;
import com.adithya.watertracker.exception.InvalidEditException;
import com.adithya.watertracker.exception.UserNotFoundException;
import com.adithya.watertracker.exception.WaterAlreadyExistsException;
import com.adithya.watertracker.repository.UserRepository;
import com.adithya.watertracker.repository.WaterRepository;
import com.adithya.watertracker.util.UnitConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WaterServiceImpl implements WaterService {

    @Autowired
    private WaterRepository waterRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public WaterIntake addWaterEntry(
            WaterRequest request,
            Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (waterRepository.findByUserAndIntakeDate(
                user,
                LocalDate.now()).isPresent()) {

            throw new WaterAlreadyExistsException(
                    "You already added today's intake. Delete today's entry and enter it again."
            );
        }

        WaterIntake intake = new WaterIntake();

        intake.setQuantity(request.getQuantity());

        intake.setUnit(request.getUnit());

        intake.setQuantityInMl(
                UnitConverter.convertToMl(
                        request.getQuantity(),
                        request.getUnit()
                )
        );

        intake.setUser(user);

        return waterRepository.save(intake);
    }
    
    @Override
    public Page<WaterIntake> getHistory(
            Long userId,
            int page,
            int size) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        Pageable pageable =
                PageRequest.of(page, size);

        return waterRepository.findByUserOrderByIntakeDateDesc(
                user,
                pageable
        );

    }
    
    @Override
    public WaterIntake updateWaterEntry(
            Long waterId,
            Long userId,
            WaterRequest request) {

        WaterIntake intake = waterRepository.findById(waterId)
                .orElseThrow(() ->
                        new RuntimeException("Water entry not found"));

        if (!intake.getUser().getId().equals(userId)) {

            throw new RuntimeException("Unauthorized access");

        }

        LocalDate today = LocalDate.now();

        if (intake.getIntakeDate().isEqual(today)) {

            throw new InvalidEditException(
                    "Today's entry cannot be edited. Delete today's entry and enter it again."
            );

        }

        if (intake.getIntakeDate().isAfter(today)) {

            throw new InvalidEditException(
                    "Future entries cannot be edited."
            );

        }

        intake.setQuantity(request.getQuantity());

        intake.setUnit(request.getUnit());

        intake.setQuantityInMl(
                UnitConverter.convertToMl(
                        request.getQuantity(),
                        request.getUnit()
                )
        );

        return waterRepository.save(intake);

    }
    
    @Override
    public void deleteEntry(Long intakeId, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        WaterIntake intake = waterRepository.findById(intakeId)
                .orElseThrow(() ->
                        new RuntimeException("Water entry not found"));

        if (!intake.getUser().getId().equals(userId)) {
            throw new RuntimeException("This entry does not belong to the user");
        }

        waterRepository.delete(intake);
    }
    
    @Override
    public CompareResponse compareDates(
            Long userId,
            LocalDate date1,
            LocalDate date2) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        WaterIntake intake1 = waterRepository
                .findByUserAndIntakeDate(user, date1)
                .orElseThrow(() ->
                        new RuntimeException("No water intake found for " + date1));

        WaterIntake intake2 = waterRepository
                .findByUserAndIntakeDate(user, date2)
                .orElseThrow(() ->
                        new RuntimeException("No water intake found for " + date2));

        Double difference = Math.abs(
                intake1.getQuantityInMl() - intake2.getQuantityInMl()
        );

        return new CompareResponse(
                date1,
                intake1.getQuantityInMl(),
                date2,
                intake2.getQuantityInMl(),
                difference,
                "ML"
        );
    }
    
   
}