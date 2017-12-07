require 'test_helper'

class UserEnrollmentsControllerTest < ActionDispatch::IntegrationTest
  setup do
    @user_enrollment = user_enrollments(:one)
  end

  test "should get index" do
    get user_enrollments_url
    assert_response :success
  end

  test "should get new" do
    get new_user_enrollment_url
    assert_response :success
  end

  test "should create user_enrollment" do
    assert_difference('UserEnrollment.count') do
      post user_enrollments_url, params: { user_enrollment: { course_department_id: @user_enrollment.course_department_id, lab_group_id: @user_enrollment.lab_group_id, study_year_id: @user_enrollment.study_year_id, user_id: @user_enrollment.user_id } }
    end

    assert_redirected_to user_enrollment_url(UserEnrollment.last)
  end

  test "should show user_enrollment" do
    get user_enrollment_url(@user_enrollment)
    assert_response :success
  end

  test "should get edit" do
    get edit_user_enrollment_url(@user_enrollment)
    assert_response :success
  end

  test "should update user_enrollment" do
    patch user_enrollment_url(@user_enrollment), params: { user_enrollment: { course_department_id: @user_enrollment.course_department_id, lab_group_id: @user_enrollment.lab_group_id, study_year_id: @user_enrollment.study_year_id, user_id: @user_enrollment.user_id } }
    assert_redirected_to user_enrollment_url(@user_enrollment)
  end

  test "should destroy user_enrollment" do
    assert_difference('UserEnrollment.count', -1) do
      delete user_enrollment_url(@user_enrollment)
    end

    assert_redirected_to user_enrollments_url
  end
end
