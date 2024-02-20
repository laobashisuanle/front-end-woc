package com.example.mybetis_plus_demo.controller;




import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.mybetis_plus_demo.common.Paging;
import com.example.mybetis_plus_demo.common.Result;
import com.example.mybetis_plus_demo.pojo.User;
import com.example.mybetis_plus_demo.service.UserService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github.pagehelper.page.PageMethod.startPage;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author laoba
 * @since 2024-02-05
 */
@RestController
@RequestMapping("/user")
public class UserController {

   @Autowired
    private UserService userService;

    @GetMapping
    public Result<List<User>> getList(){
        return Result.suc(userService.getList());
    }

    @PostMapping("/page")
    @ResponseBody
    public Result<Paging> getByPage(@RequestParam(value = "pageNum", required = false) Integer pageNum,
                                    @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                    @RequestParam(value = "number",required = false) String number,
                                    @RequestParam(value = "department",required = false) String department,
                                    @RequestParam(value = "roleId",required = false) Integer roleId){
       if(pageSize==null){
           pageSize=5;
       }
       if(pageNum==null){
           pageNum=1;
       }

       if(number==null){
           number="%";
       }
    if(department==null) {
        department = "%";
    }
    Paging paging = userService.getByPageObscure(pageNum, pageSize, number, department, roleId);

        if(paging==null){
            return Result.fail(400,"未查询到数据");
        }
        return Result.suc(paging);

    }


    @GetMapping("/{id}")
   public Result<User> getById(@PathVariable("id") Integer id){
       return Result.suc(1,userService.getById(id));
   }


   @PostMapping
   public Result save(@RequestBody User user){
       return userService.save(user)?Result.suc():Result.fail(400,"未成功添加");
   }

   @PutMapping
    public Result update(@RequestBody User user){
       return userService.update(user)?Result.suc(user):Result.fail(400,"未成功修改数据");
   }

   @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id")Integer id){
       return userService.delete(id)?Result.suc():Result.fail(400,"未成功删除");
   }

   @GetMapping("/getByNo")
   public Result getByNo(@RequestParam("number") String number){
        User users= userService.getByNo(number);
        if(users==null){
            return Result.fail(400,"未查询到数据");
        }

        return Result.suc(users);
   }



//
//@GetMapping("/getNo")
//@ResponseBody
//    public Result<List<User>> getByObscureNo(@RequestParam("number") String number){
//        List<User> users =userService.getByObscureNo(number);
//        if(users==null) {
//            return  Result.fail(400,"未查询到数据");
//        }
//        return Result.suc(users);
//    }
//
//
//
//
//
//    @GetMapping("/getDepartment")
//    @ResponseBody
//    public Result<List<User>> getByObscureDepartment(@RequestParam("department") String department){
//        List<User> users =userService.getByObscureDepartment(department);
//        if(users==null) {
//            return  Result.fail(400,"未查询到数据");
//        }
//        return Result.suc(users);
//    }
//

}

