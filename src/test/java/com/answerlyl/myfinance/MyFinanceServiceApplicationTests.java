package com.answerlyl.myfinance;

import com.answerlyl.myfinance.entity.AttrSpec;
import com.answerlyl.myfinance.entity.Finance;
import com.answerlyl.myfinance.mapper.AttrSpecMapper;
import com.answerlyl.myfinance.mapper.FinanceMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
class MyFinanceServiceApplicationTests {

	@Autowired
	private FinanceMapper financeMapper;

	@Autowired
	private AttrSpecMapper attrSpecMapper;

	@Test
	void contextLoads() {
		List<Finance> finances = financeMapper.selectList(null);
		//Assert.assertEquals(1, finances.size());
		finances.forEach(System.out::println);


	}

	//@Test
	void testInsert(){
		Finance finance = new Finance();
		finance.setCreateDate(new Date().toString());
//		finance.setAmount(100.1);
//		finance.setBelong(1.1);
//		finance.setInputDate(new Date().toString());
//		finance.setSort(1.1);
//		finance.setSortDetail(2.2);
		financeMapper.insert(finance);
	}

	//@Test
	void testSelectAttr(){

		List<AttrSpec> attrSpecs = attrSpecMapper.selectList(null);

		attrSpecs.forEach(System.out::println);


	}


}
