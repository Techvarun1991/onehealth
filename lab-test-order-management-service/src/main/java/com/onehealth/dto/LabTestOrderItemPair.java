package com.onehealth.dto;

import com.onehealth.entity.LabOrderItem;
import com.onehealth.entity.LabTestsOrder;

public class LabTestOrderItemPair {
    private LabTestsOrder order;
    private LabOrderItem item;

    public LabTestOrderItemPair(LabTestsOrder order, LabOrderItem item) {
        this.order = order;
        this.item = item;
    }

	@Override
	public String toString() {
		return "LabTestOrderItemPair [order=" + order + ", item=" + item + "]";
	}

	public LabTestOrderItemPair() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LabTestsOrder getOrder() {
		return order;
	}

	public void setOrder(LabTestsOrder order) {
		this.order = order;
	}

	public LabOrderItem getItem() {
		return item;
	}

	public void setItem(LabOrderItem item) {
		this.item = item;
	}

    // Getter and setter methods
    
}

