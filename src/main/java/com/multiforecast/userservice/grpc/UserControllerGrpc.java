package com.multiforecast.userservice.grpc;

import com.multiforecast.userservice.Empty;
import com.multiforecast.userservice.LocationSaveRequest;
import com.multiforecast.userservice.UserServiceGrpc;
import com.multiforecast.userservice.service.UserService;
import com.multiforecast.userservice.service.dto.Location;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;

@Slf4j
@GRpcService
@RequiredArgsConstructor
public class UserControllerGrpc extends UserServiceGrpc.UserServiceImplBase {

    private final UserService userService;

    @Override
    public void saveLocation(final LocationSaveRequest request, final StreamObserver<Empty> responseObserver) {
        log.info("Got location save request {}", request);

        userService.saveOrUpdateLocation(request.getUserId(),
                new Location(null, Float.valueOf(request.getLon()), Float.valueOf(request.getLat())));
    }
}