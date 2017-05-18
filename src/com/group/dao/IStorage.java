package com.group.dao;

import com.group.exception.TradeNotFoundException;
import com.group.exception.UserNotFoundException;

interface IStorage <T>
{	
	void create(T u);
	T read(int id) throws UserNotFoundException, TradeNotFoundException;
	void update(T u);
	void delete(int id) throws UserNotFoundException, TradeNotFoundException;
}