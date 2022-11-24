package com.multiforecast.userservice.grpc;

import com.multiforecast.userservice.*;
import com.multiforecast.userservice.enums.Field;
import com.multiforecast.userservice.service.UserService;
import com.multiforecast.userservice.service.dto.Location;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;

import java.util.Arrays;

@Slf4j
@GRpcService
@RequiredArgsConstructor
public class UserControllerGrpc extends UserServiceGrpc.UserServiceImplBase {

    private final UserService userService;

    @Override
    public void saveLocation(final LocationSaveRequest request, final StreamObserver<Empty> responseObserver) {
        log.info("Received location save request {}", request);

        userService.saveOrUpdateLocation(request.getUserId(),
                new Location(null, Float.valueOf(request.getLon()), Float.valueOf(request.getLat())));

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void getFields(final Empty request, final StreamObserver<GetFieldResponse> responseObserver) {
        log.info("Received field show request {}", request);
        GetFieldResponse.Builder builder = GetFieldResponse.newBuilder();
        builder.addAllFields(Arrays.stream(Field.values()).map(Field::name).toList());
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void saveFieldPrefs(final SaveFieldRequest request, final StreamObserver<Empty> responseObserver) {
        log.info("Received field save request {}", request);

        userService.saveFieldPrefs(request.getUserId(), request.getFieldsList().stream().map(Field::valueOf).toList());

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void getUserSearchPath(final UserSearchPathRequest request, final StreamObserver<UserSearchPathResponse> responseObserver) {
        log.info("Received search path request {}", request);

        String userSearchPath = userService.getUserSearchPath(request.getUserId());

        responseObserver.onNext(UserSearchPathResponse.newBuilder().setSearchPath(userSearchPath).build());
        responseObserver.onCompleted();
    }
}