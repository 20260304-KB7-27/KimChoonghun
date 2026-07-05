package org.scoula.controller;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
public class HomeController {

    @Autowired
    BoardService service;

    @GetMapping("/")
    public String home() {
        // log.info("================> HomController /");
        // return "index"; // View의 이름
        return "redirect:/board/list";
    }

    @GetMapping({ "/get", "/update" })
    public void get(@RequestParam("no") Long no, Model model) {
        model.addAttribute("board", service.get(no));
    }

    @PostMapping("/create")
    public String create(BoardDTO board,
                         RedirectAttributes ra) {
        service.create(board);
        ra.addFlashAttribute("result", board.getNo());
        return "redirect:/board/list";
    }

    @PostMapping("/update")
    public String update(BoardDTO board,
                         RedirectAttributes ra) {
        if (service.update(board)) {
            ra.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("no") Long no,
                         RedirectAttributes ra) {
        if (service.delete(no)) {
            ra.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";
    }
}