package com.multiforecast.userservice.grpc;

import com.multiforecast.userservice.LocationRequest;
import com.multiforecast.userservice.LocationResponse;
import com.multiforecast.userservice.LocationServiceGrpc;
import com.multiforecast.userservice.service.LocationService;
import com.multiforecast.userservice.service.dto.LocationDTO;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
@RequiredArgsConstructor
public class LocationControllerGrpc extends LocationServiceGrpc.LocationServiceImplBase{

    private final LocationService locationService;

    @Override
    public void getLocation(final LocationRequest request, final StreamObserver<LocationResponse> responseObserver) {
        final LocationDTO location = locationService.getLocation(request.getUserId());
        responseObserver.onNext(LocationResponse.newBuilder()
                .setLat(location.lat())
                .setLon(location.lon())
                .build());
        responseObserver.onCompleted();
    }
}
