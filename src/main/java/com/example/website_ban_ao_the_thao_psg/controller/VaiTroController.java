package com.example.website_ban_ao_the_thao_psg.controller;

import com.example.website_ban_ao_the_thao_psg.entity.MauSac;
import com.example.website_ban_ao_the_thao_psg.entity.VaiTro;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateMauSacRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.create_request.CreateVaiTroRequest;
import com.example.website_ban_ao_the_thao_psg.model.request.update_request.UpdateMauSacRequest;
import com.example.website_ban_ao_the_thao_psg.model.response.MauSacResponse;
import com.example.website_ban_ao_the_thao_psg.model.response.VaiTroResponse;
import com.example.website_ban_ao_the_thao_psg.service.MauSacService;
import com.example.website_ban_ao_the_thao_psg.service.VaiTroService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
@Controller
@RequestMapping("/admin/psg/vai-tro")
public class VaiTroController {
    @Autowired
    VaiTroService vaiTroService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, HttpSession session) {
        if (session.getAttribute("successMessage") != null) {
            String successMessage = (String) session.getAttribute("successMessage");
            model.addAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }
        model.addAttribute("vaiTro", new VaiTro());
        return pageVaiTroActive(0, model);
    }

    @GetMapping("/pageActive/{pageNo}")
    public String pageVaiTroActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        model.addAttribute("vaiTro", new VaiTro());
        Page<VaiTroResponse> vaiTroResponsePageActive = vaiTroService.pageVaiTroActive(pageNo, 3);
        model.addAttribute("size", vaiTroResponsePageActive.getSize());
        model.addAttribute("totalPages", vaiTroResponsePageActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listMauSacActive", vaiTroResponsePageActive);
        return "admin/vai_tro/trang_chu_vai_tro";
    }

    @GetMapping("/pageInActive/{pageNo}")
    public String pageMauSacInActive(@PathVariable("pageNo") Integer pageNo, Model model) {
        Page<VaiTroResponse> vaiTroResponsePageInActive = vaiTroService.pageVaiTroInActive(pageNo, 3);
        model.addAttribute("size", vaiTroResponsePageInActive.getSize());
        model.addAttribute("totalPages", vaiTroResponsePageInActive.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listMauSacInActive", vaiTroResponsePageInActive);
        return "admin/vai_tro/revert_vai_tro";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") Integer id, Model model) {
        VaiTroResponse vaiTroResponse = vaiTroService.getOne(id);
        model.addAttribute("vaiTro", vaiTroResponse);
        return "admin/vai_tro/view_update_vai_tro";
    }
    @GetMapping("/view-add")
    public String viewAdd(Model model) {
        model.addAttribute("mauSac", new CreateVaiTroRequest());
        return "admin/vai_tro/view_add_mau_sac";
    }
    @PostMapping("/delete/{id}")
    public String deleteMauSac(@PathVariable("id") Integer id,HttpSession session) {
        mauSacService.deleteMauSac(id, LocalDate.now());
        session.setAttribute("successMessage", "Xóa thành công!");
        return "redirect:/admin/psg/mau-sac/hien-thi";
    }

    @PostMapping("/revert/{id}")
    public String revertMauSac(@PathVariable("id") Integer id,HttpSession session) {
        mauSacService.revertMauSac(id, LocalDate.now());
        session.setAttribute("successMessage", "Khôi phục thành công!");
        return "redirect:/admin/psg/mau-sac/hien-thi";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("mauSac") CreateMauSacRequest createMauSacRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("mauSac", createMauSacRequest);
            return "admin/mau_sac/view_add_mau_sac";
        }
        mauSacService.add(createMauSacRequest);
        session.setAttribute("successMessage", "Thêm thành công!");
        return "redirect:/admin/psg/mau-sac/hien-thi";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("mauSac") UpdateMauSacRequest updateMauSacRequest, BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()){
            model.addAttribute("mauSac", updateMauSacRequest);
            return "admin/mau_sac/view_update_mau_sac";
        }
        mauSacService.update(updateMauSacRequest);
        session.setAttribute("successMessage", "Cập nhập thành công!");
        return "redirect:/admin/psg/mau-sac/hien-thi";
    }

    @GetMapping("/searchActive/{pageNo}")
    public String searchActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("mauSac", new MauSac());
        Page<MauSacResponse> mauSacResponsePage = mauSacService.searchNameOrMaActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", mauSacResponsePage.getSize());
        model.addAttribute("totalPages", mauSacResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listMauSacActive", mauSacResponsePage);
        return "admin/mau_sac/trang_chu_mau_sac";
    }
    @GetMapping("/searchInActive/{pageNo}")
    public String searchInActive(Model model, @PathVariable("pageNo") Integer pageNo,  @RequestParam("searchNameOrMa") String searchNameOrMa){
        model.addAttribute("mauSac", new MauSac());
        Page<MauSacResponse> mauSacResponsePage = mauSacService.searchNameOrMaInActive(searchNameOrMa, pageNo, 3);
        model.addAttribute("size", mauSacResponsePage.getSize());
        model.addAttribute("totalPages", mauSacResponsePage.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listMauSacInActive", mauSacResponsePage);
        return "admin/mau_sac/revert_mau_sac";
    }
}
