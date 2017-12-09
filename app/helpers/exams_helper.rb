module ExamsHelper

  def NastavnoIspiti(user_id, course_department_id)
    user = User.find(user_id)
    var=true
    if user.role_id.equal?(4)
      course_department = CourseDepartment.where("id=?",course_department_id).pluck(:course_id)[0]
      var = Course.where("responsible=? and id=?",user_id, course_department).exists?
    end
    return var
  end

  def StudentIspiti?(user_id, course_department_id)
    user = User.find(user_id)
    if user.role_id.equal?(2)
      var = UserEnrollment.where("user_id=? and course_department_id=?",user_id, course_department_id).exists?
      #course_department = CourseDepartment.where("id=?",course_department_id).pluck(:course_id)[0]
    end
    return var
  end

end
