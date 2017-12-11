module NoticesHelper
  def stavioObavijest?(user_id, notice_id)
    korisnik= User.find(user_id)

    #pronaci course_dep_id
    kurs_id = CourseDepartment.find_by_course_id(notice_id)

    var = UserEnrollment.where("user_id=? and course_department_id=?",user_id, kurs_id).exists?

    puts var
    if korisnik.role_id.equal?(1)
     # puts korisnik.role_id
      var = true
    end
    if korisnik.role_id.equal?(4)
      # puts korisnik.role_id
      var = Notice.where("user_id=? and course_id=?",user_id,notice_id ).exists?
    end

    return var
  end
end
