require 'test_helper'

class StudentDetailsControllerTest < ActionDispatch::IntegrationTest
  setup do
    @student_detail = student_details(:one)
  end

  test "should get index" do
    get student_details_url
    assert_response :success
  end

  test "should get new" do
    get new_student_detail_url
    assert_response :success
  end

  test "should create student_detail" do
    assert_difference('StudentDetail.count') do
      post student_details_url, params: { student_detail: { budget: @student_detail.budget, disrollmentDate: @student_detail.disrollmentDate, enrollmentDate: @student_detail.enrollmentDate, graduationDate: @student_detail.graduationDate, indeks: @student_detail.indeks, user_id: @student_detail.user_id } }
    end

    assert_redirected_to student_detail_url(StudentDetail.last)
  end

  test "should show student_detail" do
    get student_detail_url(@student_detail)
    assert_response :success
  end

  test "should get edit" do
    get edit_student_detail_url(@student_detail)
    assert_response :success
  end

  test "should update student_detail" do
    patch student_detail_url(@student_detail), params: { student_detail: { budget: @student_detail.budget, disrollmentDate: @student_detail.disrollmentDate, enrollmentDate: @student_detail.enrollmentDate, graduationDate: @student_detail.graduationDate, indeks: @student_detail.indeks, user_id: @student_detail.user_id } }
    assert_redirected_to student_detail_url(@student_detail)
  end

  test "should destroy student_detail" do
    assert_difference('StudentDetail.count', -1) do
      delete student_detail_url(@student_detail)
    end

    assert_redirected_to student_details_url
  end
end
