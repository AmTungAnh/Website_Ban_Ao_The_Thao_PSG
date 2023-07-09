package com.example.website_ban_ao_the_thao_psg.service;

import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateLoaiSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateLoaiSanPhamRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.LoaiSanPhamResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface LoaiSanPhamService {
    Page<LoaiSanPhamResponse> pageLoaiSanPhamActive(Integer pageNo, Integer size);

    Page<LoaiSanPhamResponse> pageLoaiSanPhamInActive(Integer pageNo, Integer size);

    LoaiSanPhamResponse add(CreateLoaiSanPhamRequest createLoaiSanPhamRequest);

    LoaiSanPhamResponse update(UpdateLoaiSanPhamRequest updateLoaiSanPhamRequest);

    LoaiSanPhamResponse getOne(Integer id);

    Page<LoaiSanPhamResponse> searchNameOrMa(String searchName, Integer pageNo, Integer size);

    void deleteLoaiSanPham(Integer id, LocalDate now);

    void revertLoaiSanPham(Integer id,LocalDate now);
}
