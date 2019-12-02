package com.benwunet.mws.commons.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 * @author xaingkaihong
 * @date 2019-04-27 9:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> implements Serializable {

	private static final long serialVersionUID = -2007865993854784183L;
	private int total;
	private List<T> data;
}
