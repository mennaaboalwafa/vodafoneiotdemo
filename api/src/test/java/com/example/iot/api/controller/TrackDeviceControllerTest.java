package com.example.iot.api.controller;

import com.example.iot.domain.entity.Countries;
import com.example.iot.domain.entity.Sim;
import com.example.iot.service.constants.DeviceStatusEnum;
import com.example.iot.service.model.TrackDevicePOJO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(TrackDeviceController.class)
class TrackDeviceControllerTest {

    private final MockMvc mvc;

    @MockBean
    private TrackDeviceController trackDeviceController;

    @Autowired
    TrackDeviceControllerTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    @Test
    void findInactiveDevices() throws Exception {

        Countries country = new Countries(1, "UK");
        Sim sim = new Sim(1, "02", 0, "01005726717", country);
        TrackDevicePOJO trackDevicePOJO = new TrackDevicePOJO(1, 50, sim, 0);
        List<TrackDevicePOJO> trackDevicePOJOList = Collections.singletonList(trackDevicePOJO);
        Page<TrackDevicePOJO> inActiveTrackDevices = new PageImpl<>(trackDevicePOJOList);
        ResponseEntity<Page<TrackDevicePOJO>> pageResponseEntity = new ResponseEntity<>(inActiveTrackDevices, HttpStatus.OK);
        given(trackDeviceController.findInactiveDevices()).willReturn(pageResponseEntity);

        mvc.perform(get("/trackDevice/inactiveDevices")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content",
                        hasSize(equalTo(1))))
        ;
    }

    @Test
    void update() throws Exception {
        Countries country = new Countries(1, "UK");
        Sim sim = new Sim(1, "02", 1, "01005726716", country);
        TrackDevicePOJO trackDevicePOJO = new TrackDevicePOJO(3, 60, sim, 1);
        ResponseEntity<TrackDevicePOJO> responseEntity = new ResponseEntity<>(trackDevicePOJO, HttpStatus.OK);
        given(trackDeviceController.update(3L, DeviceStatusEnum.READY)).willReturn(responseEntity);

        mvc.perform(put("/trackDevice/3/READY")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"trackDeviceId\":3,\"deviceStatusEnum\":\"READY\",\"trackDeviceTemp\":60,\"simPOJO\":{\"simId\":1,\"simOperatorCode\":\"02\",\"simStatus\":\"ACTIVE\",\"country\":{\"countryName\":\"UK\"},\"simNum\":\"01005726716\"}}"))
        ;
    }

    @Test
    void delete() throws Exception {
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        given(trackDeviceController.delete(2L)).willReturn(responseEntity);

        mvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/trackDevice/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void findActiveDevices() throws Exception {
        Countries country = new Countries(1, "UK");
        Sim sim = new Sim(1, "02", 1, "01005726717", country);
        TrackDevicePOJO trackDevicePOJO = new TrackDevicePOJO(1, 50, sim, 1);
        List<TrackDevicePOJO> trackDevicePOJOList = Collections.singletonList(trackDevicePOJO);
        Page<TrackDevicePOJO> inActiveTrackDevices = new PageImpl<>(trackDevicePOJOList);
        ResponseEntity<Page<TrackDevicePOJO>> pageResponseEntity = new ResponseEntity<>(inActiveTrackDevices, HttpStatus.OK);
        given(trackDeviceController.findInactiveDevices()).willReturn(pageResponseEntity);

        mvc.perform(get("/trackDevice/inactiveDevices")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content",
                        hasSize(equalTo(1))))
        ;
    }
}