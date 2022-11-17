package com.multiforecast.userservice.grpc;

import com.google.protobuf.Any;
import com.google.rpc.Status;
import com.multiforecast.userservice.LocationRequest;
import com.multiforecast.userservice.LocationResponse;
import com.multiforecast.userservice.LocationServiceGrpc;
import com.multiforecast.userservice.UserNotFoundError;
import com.multiforecast.userservice.service.LocationService;
import com.multiforecast.userservice.service.UserService;
import com.multiforecast.userservice.service.dto.Location;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;

@Slf4j
@GRpcService
@RequiredArgsConstructor
public class LocationControllerGrpc extends LocationServiceGrpc.LocationServiceImplBase{

    private final LocationService locationService;
    private final UserService userService;

    @Override
    public void getLocation(final LocationRequest request, final StreamObserver<LocationResponse> responseObserver) {
        log.info("Got location request {}", request);
        if (userService.userExists(request.getUserId())) {
            final Location location = locationService.getLocation(request.getUserId());
            responseObserver.onNext(LocationResponse.newBuilder()
                    .setLat(location.lat())
                    .setLon(location.lon())
                    .build());
            responseObserver.onCompleted();
        } else {
            Status status = Status.newBuilder()
                    .setCode(com.google.rpc.Code.NOT_FOUND.getNumber())
                    .setMessage("User not found")
                    .addDetails(Any.pack(UserNotFoundError.newBuilder()
                            .setUserId(request.getUserId())
                            .build()))
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }
}
