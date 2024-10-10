package com.example.project360;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Testing {
	
	static int failCount = 0;
	static int passCount = 0;
	public static void main(String[] args) {
		System.out.println("Starting Testing...");
		
		
		
		
		// Tests the creation of a new user and the recall of their username and password
		System.out.println();
		System.out.println("Creating a new User...");
		String testUsername = "TestUser1";
		String testPassword = "TestPass84%%";
		User newUser = new User(testUsername, testPassword);
		
		System.out.println();
		System.out.println("Test User's username: " + newUser.getUsername());
		System.out.println("Test User's password: " + newUser.getPassword());
		
		System.out.println();
		compareResult(testUsername, newUser.getUsername());
		compareResult(testPassword, newUser.getPassword());
		
		
		
		
		// Tests the setting and recall of user name and email details
		String testEmail = "TestEmail@email.com";
		String testFirstName = "FName";
		String testMiddleName = "MName";
		String testLastName = "LName";
		String testPrefName = "PrerFName";
		newUser.setAccountDetails(testEmail, testFirstName, testMiddleName, testLastName, testPrefName);
		
		System.out.println();
		System.out.println("Test User's name: " + newUser.getFirstName() + newUser.getLastName());
		System.out.println("Test User's prefName: " + newUser.getPreferredFirstName());
		System.out.println("Test User's email" + newUser.getEmail());
		
		System.out.println();
		compareResult(testFirstName, newUser.getFirstName());
		compareResult(testMiddleName, newUser.getMiddleName());
		compareResult(testLastName, newUser.getLastName());
		compareResult(testEmail, newUser.getEmail());
		compareResult(testPrefName, newUser.getPreferredFirstName());
		
		
		
		
		
		// Test the adding of a role to a user
		// Tests to see if the list contains one that was added, and one that wasn't added
		String roleName = "TestRole";
		String nonAddedRoleName = "AnotherRole";
		
		System.out.println();
		System.out.println("Adding role " + roleName + " to the user...");
		
		newUser.addRole(roleName);
		
		System.out.println();
		if (newUser.getRoles().contains(roleName)) {
			System.out.println("Test PASSED: " + roleName + " role successfully added to user");
			passCount++;
		} else {
			System.out.println("Test FAILED: " + roleName + " role was not successfully added to user");
			failCount++;
		}
		System.out.println();
		if (!newUser.getRoles().contains(nonAddedRoleName)) {
			System.out.println("Test PASSED: " + roleName + " role was not in user's list (it should not have been)");
			passCount++;
		} else {
			System.out.println("Test FAILED: " + roleName + " was in user's list (it should not have been)");
			failCount++;
		}
		
		
		
		
		// Tests the removing of the previously added role
		System.out.println();
		System.out.println("Removing role " + roleName + " to the user...");
		
		newUser.removeRole(roleName);
		System.out.println();
		if (!newUser.getRoles().contains(roleName)) {
			System.out.println("Test PASSED: " + roleName + " role successfully removed from user");
			passCount++;
		} else {
			System.out.println("Test FAILED: " + roleName + " role was not successfully removed from user");
			failCount++;
		}
		
		
		
		// Tests the creation of an admin
		System.out.println();
		System.out.println("Creating a new Admin...");
		List<User> userList = new ArrayList<>();
		userList.add(newUser);
		String testAdminUsername = "TestAdmin1";
		String testAdminPassword = "TestPass33*";
		Admin newAdmin = new Admin(testAdminUsername, testAdminPassword, userList);
		
		String testAdminEmail = "A_TestEmail@email.com";
		String testAdminFirstName = "A_FName";
		String testAdminMiddleName = "A_MName";
		String testAdminLastName = "A_LName";
		String testAdminPrefName = "A_PrerFName";
		newUser.setAccountDetails(testAdminEmail, testAdminFirstName, testAdminMiddleName, testAdminLastName, testAdminPrefName);
		
		System.out.println();
		System.out.println("Test Admin's username: " + newAdmin.getUsername());
		System.out.println("Test Admin's password: " + newAdmin.getPassword());
		
		System.out.println();
		compareResult(testAdminUsername, newAdmin.getUsername());
		compareResult(testAdminPassword, newAdmin.getPassword());
		
		
		
		
		// Tests the generation of unique invite codes
		// Must wait at least some number of milliseconds in between generating codes
		System.out.println();
		System.out.println("Generating Invite Codes");
		
		String inviteCode1 = newAdmin.generateInvitationCode();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String inviteCode2 = newAdmin.generateInvitationCode();
		
		if (!inviteCode1.equals(inviteCode2)) {
			System.out.println("Test PASSED: " + inviteCode1 + " and " + inviteCode2 + " are unique");
			passCount++;
		} else {
			System.out.println("Test FAILED: " + inviteCode1 + " and " + inviteCode2 + " are not unique");
			failCount++;
		}
		
		
		
		
		// Tests the generation of unique one time passwords
		System.out.println();
		System.out.println("Generating OneTimePasswords Codes");
		
		String OTP1 = newAdmin.generateOneTimePassword();
		String OTP2 = newAdmin.generateOneTimePassword();
		
		if (!OTP1.equals(OTP2)) {
			System.out.println("Test PASSED: " + OTP1 + " and " + OTP2 + " are unique");
			passCount++;
		} else {
			System.out.println("Test FAILED: " + OTP1 + " and " + OTP2 + " are not unique");
			failCount++;
		}
		
		
		
		
		
		// Tests the adding of a role to another user
		String otherUserRole = "OtherUser";
		System.out.println();
		System.out.println("Adding role " + otherUserRole + " to a different user");
		
		newAdmin.addRoleToUser(newUser, otherUserRole);
		
		System.out.println();
		if (newUser.getRoles().contains(otherUserRole)) {
			System.out.println("Test PASSED: " + roleName + " role successfully added to user");
			passCount++;
		} else {
			System.out.println("Test FAILED: " + roleName + " role was not successfully added to user");
			failCount++;
		}
		
		
		
		// Tests the removal of a role from another user
		System.out.println();
		System.out.println("Removing role " + otherUserRole + " from a different user");
		
		newAdmin.removeRoleFromUser(newUser, otherUserRole);
		
		System.out.println();
		if (!newUser.getRoles().contains(otherUserRole)) {
			System.out.println("Test PASSED: " + roleName + " role successfully removed from user");
			passCount++;
		} else {
			System.out.println("Test FAILED: " + roleName + " role was not successfully removed from user");
			failCount++;
		}
		
		
		// Tests the inviting of a new User
		System.out.println();
		System.out.println("Inviting a new User...");
		
		String user2Username = "User2";
		newAdmin.inviteUser(user2Username, roleName, testFirstName, testLastName, testEmail, testPrefName);
		
		boolean foundUser = false;
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getUsername().equals(user2Username)) {
				foundUser = true;
				break;
			}
		}
		
		if (foundUser) {
			System.out.println("Test PASSED: " + user2Username + " successfully invited by admin");
			passCount++;
		} else {
			System.out.println("Test FAILED: " + user2Username + " not successfully invited by admin");
			failCount++;
		}
		

		
		
		
		
		
		
		
		System.out.println();
		System.out.println();
		System.out.println("----------Testing Complete---------");
		System.out.println(passCount + " tests Passed");
		System.out.println(failCount + " tests Failed");
	}
	
	public static void compareResult(String str1, String str2) {
		if (str1.equals(str2)) {
			System.out.println("Test PASSED: " + str1 + " matched " + str2);
			passCount++;
		} else {
			System.out.println("Test FAILED: " + str1 + " did not match " + str2);
			failCount++;
		}
	}
}
