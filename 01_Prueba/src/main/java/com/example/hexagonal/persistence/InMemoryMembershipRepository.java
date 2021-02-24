package com.example.hexagonal.persistence;

import java.util.AbstractMap;
import java.util.HashMap;

import com.example.hexagonal.common.MembershipRepository;

public class InMemoryMembershipRepository implements MembershipRepository {

	private AbstractMap<Integer, String> members;
	
	
	public InMemoryMembershipRepository() {		
		this.members = new HashMap<Integer, String>();
	}

	@Override
	public boolean isRegistered(int membershipId) {
		// TODO Auto-generated method stub
		return this.members.containsKey(membershipId);
	}

	@Override
	public void registerMember(int membershipId, String name) {
		// TODO Auto-generated method stub
		this.members.put(membershipId, name);
	}

}
