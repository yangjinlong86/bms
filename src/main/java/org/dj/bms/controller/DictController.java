package org.dj.bms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dj.bms.model.Node;
import org.dj.bms.utils.ECacheUtils;
import org.dj.bms.utils.ResponseMsg;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DictController
 * @Description: TODO
 * @author pufangfei@163.com
 * @date 2017年11月11日 上午11:14:14
 */
@RestController
public class DictController extends BaseController {
	
	@Value("${select.limit}")
	private int limit = 10; 
	/**
	 * 
	* @Title: testEache
	* @Description: 通过关键字得到字典的值
	* @param pid 字典类型
	* @param key 关键字
	* @param size 返回条数 默认为 10条
	* @return Object    
	* @throws
	 */
	@RequestMapping("dict/getValues/{pid}/{key}/{size}")
	public Object dictEache(@PathVariable("pid") String pid,
			@PathVariable("key") String key,
			@PathVariable("size") int size) {
		Map<String, Node> dict = ECacheUtils.getDictCache();
		List<Node> nodes = new ArrayList<>();
		int sum = size == 0? limit :size;
		dict.forEach((k, v) -> {
			if (StringUtils.startsWith(k, pid)) {
				if (StringUtils.contains(k, key)) {
					if (sum == nodes.size()) {
						return;
					}
					nodes.add(v);
				}
			}
		});
		ResponseMsg res = getRes(true);
		res.setData(nodes);
		return res;
	}
}