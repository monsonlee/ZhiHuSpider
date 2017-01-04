package domain;

/**
 * User用于存储用户信息
 *
 * @className User
 * @author mxlee
 * @email imxlee@foxmail.com
 */
public class User {

	String name;// 用户姓名

	String gender;// 性别

	String location;// 居住地;

	String business;// 行业

	String employment;// 公司

	String position;// 职位;

	String school;// 大学

	String major;// 专业

	String userInfo;// 个人简介

	Long answersNum;// 回答数量

	Long starsNum;// 被赞同数

	Long thxNum;// 被感谢数

	Long saveNum;// 被收藏数

	Long follow;// 关注了

	String followingURL;// 关注页面链接

	Long follower;// 关注者

	String followerURL;// 关注页面链接

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getEmployment() {
		return employment;
	}

	public void setEmployment(String employment) {
		this.employment = employment;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public Long getAnswersNum() {
		return answersNum;
	}

	public void setAnswersNum(Long answersNum) {
		this.answersNum = answersNum;
	}

	public Long getStarsNum() {
		return starsNum;
	}

	public void setStarsNum(Long starsNum) {
		this.starsNum = starsNum;
	}

	public Long getThxNum() {
		return thxNum;
	}

	public void setThxNum(Long thxNum) {
		this.thxNum = thxNum;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public Long getSaveNum() {
		return saveNum;
	}

	public void setSaveNum(Long saveNum) {
		this.saveNum = saveNum;
	}

	public Long getFollow() {
		return follow;
	}

	public void setFollow(Long follow) {
		this.follow = follow;
	}

	public String getFollowingURL() {
		return followingURL;
	}

	public void setFollowingURL(String followingURL) {
		this.followingURL = followingURL;
	}

	public Long getFollower() {
		return follower;
	}

	public void setFollower(Long follower) {
		this.follower = follower;
	}

	public String getFollowerURL() {
		return followerURL;
	}

	public void setFollowerURL(String followerURL) {
		this.followerURL = followerURL;
	}

	@Override
	public String toString() {
		return "姓名：" + name + "\n性别：" + gender + "\n居住地：" + location + "\n行业：" + business + "\n公司：" + employment
				+ "\n职位：" + position + "\n大学：" + school + "\n专业：" + major + "\n个人简介：" + userInfo + "\n回答数：" + answersNum
				+ "\n关注数：" + follow + "\n被关注数：" + follower + "\n被点赞数：" + starsNum + "\n被感谢数：" + thxNum + "\n被收藏数："
				+ saveNum;
	}

}
