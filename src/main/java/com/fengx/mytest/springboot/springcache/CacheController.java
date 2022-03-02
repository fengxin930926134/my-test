package com.fengx.mytest.springboot.springcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Spring4.3中引进了｛@GetMapping、@PostMapping、@PutMapping、@DeleteMapping、@PatchMapping｝
 * 来帮助简化常用的HTTP方法的映射 并更好地表达被注解方法的语义，至于@PatchMapping可以暂时不用管，Patch方式是对put方式的一种补充，put方式是可以更新.但是更新的是整体.patch是对局部更新;
 */
@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private CacheTestService cacheTestService;

    /**
     * 根据ID获取信息
     * 
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public String test(@PathVariable("id") String id) {
        return cacheTestService.get(id);
    }

    /**
     * 删除某个ID的信息
     * 
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") String id) {
        return cacheTestService.delete(id);
    }

    /**
     * 保存某个ID的信息
     * 
     * @param id
     * @return
     */
    @PostMapping
    public String save(@RequestParam("id") String id, @RequestParam("value") String value) {
        return cacheTestService.save(id, value);
    }

    /**
     * 跟新某个ID的信息
     * 
     * @param id
     * @return
     */
    @PutMapping("{id}")
    public String update(@PathVariable("id") String id, @RequestParam("value") String value) {
        return cacheTestService.update(id, value);
    }
}