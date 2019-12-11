package com.team404.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team404.command.UserVO;
import com.team404.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	@Qualifier("userService")
	private UserService userService; 
	
	//회원가입 화면
	@RequestMapping(value="/userJoin")
	public String userJoin() {
		return "user/userJoin";
	}
	
	//로그인 화면
	@RequestMapping(value="/userLogin")
	public String userLogin() {
		return "user/userLogin";
	}
	
	//마이페이지 화면(페이지 진입시, 조인을 통해서, user에 대한 정보와 user가 쓴글에 대한 정보를 동시에 처리)
	@RequestMapping(value="/userMypage")
	public String userMypage(HttpSession session, Model model) {
		String userId = (String)session.getAttribute("user_id");
		UserVO userVO = userService.getInfo(userId);//join의 결과를 resultMap으로 한번에 묶어서 처리
		//System.out.println(vo.toString());
		model.addAttribute("userVO", userVO);
		return "user/userMypage";
	}
	
	//마이페이지 수정요청
	@RequestMapping("/updateUser")
	@ResponseBody
	public String updateUser(@RequestBody UserVO vo) {
		int result = userService.updateUser(vo);	
		System.out.println(vo.toString());
		return result == 1 ? "success":"fail";
	}
	
	//일반 컨트롤러에서 @ResponseBody를 적어주면 메소드의 리턴값을
	//view리졸버로 가지 않고 해당메소드를 호출한곳으로 반환합니다.
	@RequestMapping(value="/idConfirm")
	@ResponseBody
	public int idConfirm(@RequestBody UserVO vo) {
		
		int result = userService.idConfirm(vo);
		//System.out.println(vo.toString());
			return result;
	}
	
	@RequestMapping(value="/joinForm", method=RequestMethod.POST)
	public String joinForm(UserVO vo, RedirectAttributes RA) {
		//join메소드로 insert작업을 처리
		//1.성공시 login페이지로 유도
		//2.실패시 login페이지로 유도
		//System.out.println(vo.toString());
		int result = userService.join(vo);
		
		if(result == 1) {//회원가입 성공
			RA.addFlashAttribute("msg", "회원가입을 축하합니다!");
		} else {//회원가입 실패
			RA.addFlashAttribute("msg", "회원가입에 실패했습니다.");
		}
		return "redirect:/user/userLogin";
	}
	
	//로그인 처리
	@RequestMapping(value="/loginForm")
	public String loginForm(UserVO vo, RedirectAttributes RA, HttpSession session) {

		//1개의 카운트가 나왔다는 것은 로그인 성공, 0이 나왔다는 것은 로그인 실패
		int result = userService.login(vo);
//		if(result == 1) {
//			session.setAttribute("user_id", vo.getUserId());//세션에 아이디 저장
//			return "redirect:/"; //홈화면
//		} else {			
//			RA.addFlashAttribute("msg", "아이디 비밀번호를 확인하세요.");//리다이렉트시 1회성 데이터
//			return "redirect:/user/userLogin";
//		}
		//POst핸들러와 접목시켜서 사용, 단 중복 REDIRECT 이동이 일어나면 안됩니다. 
		if(result == 1) {//로그인 성공
			session.setAttribute("user_id", vo.getUserId());//세션에 아이디 저장
			return "home"; //홈화면
		} else {//로그인 실패			
			RA.addFlashAttribute("msg", "아이디 비밀번호를 확인하세요.");//리다이렉트시 1회성 데이터
			return "redirect:/user/userLogin";
		}
		
	}
	
	//로그아웃
	@RequestMapping("/userLogout")
	public String userLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";//홈화면
	}
	
}