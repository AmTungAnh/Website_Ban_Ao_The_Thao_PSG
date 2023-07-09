package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateNuocSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateNuocSanXuatRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.NuocSanXuatResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface NuocSanXuatService {
    Page<NuocSanXuatResponse> pageNuocSanXuatActive(Integer pageNo, Integer size);

    Page<NuocSanXuatResponse> pageNuocSanXuatInActive(Integer pageNo, Integer size);

    NuocSanXuatResponse add(CreateNuocSanXuatRequest createNuocSanXuatRequest);

    NuocSanXuatResponse update(UpdateNuocSanXuatRequest updateNuocSanXuatRequest);

    NuocSanXuatResponse getOne(Integer id);

    Page<NuocSanXuatResponse> searchNameOrMaActive(String searchName, Integer pageNo, Integer size);
    Page<NuocSanXuatResponse> searchNameOrMaInActive(String searchName, Integer pageNo, Integer size);

    void deleteNuocSanXuat(Integer id, LocalDate now);

    void revertNuocSanXuat(Integer id,LocalDate now);
}
