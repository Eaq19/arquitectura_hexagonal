package com.example.hexagonal.common;

public interface MembershipRepository {

	public boolean isRegistered(int membershipId);
	
	public void registerMember(int membershipId, String name);
}
