require 'test_helper'

class ExamStudentsControllerTest < ActionDispatch::IntegrationTest
  setup do
    @exam_student = exam_students(:one)
  end

  test "should get index" do
    get exam_students_url
    assert_response :success
  end

  test "should get new" do
    get new_exam_student_url
    assert_response :success
  end

  test "should create exam_student" do
    assert_difference('ExamStudent.count') do
      post exam_students_url, params: { exam_student: { exam_id: @exam_student.exam_id, status: @exam_student.status, user_id: @exam_student.user_id } }
    end

    assert_redirected_to exam_student_url(ExamStudent.last)
  end

  test "should show exam_student" do
    get exam_student_url(@exam_student)
    assert_response :success
  end

  test "should get edit" do
    get edit_exam_student_url(@exam_student)
    assert_response :success
  end

  test "should update exam_student" do
    patch exam_student_url(@exam_student), params: { exam_student: { exam_id: @exam_student.exam_id, status: @exam_student.status, user_id: @exam_student.user_id } }
    assert_redirected_to exam_student_url(@exam_student)
  end

  test "should destroy exam_student" do
    assert_difference('ExamStudent.count', -1) do
      delete exam_student_url(@exam_student)
    end

    assert_redirected_to exam_students_url
  end
end
