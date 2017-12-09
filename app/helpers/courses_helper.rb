module CoursesHelper
  def drziKurs?(user_id, course_id)
    korisnik= User.find(user_id)
    if korisnik.role_id.equal?(4)
    var = Course.where("responsible=? and id=?",user_id, course_id).exists?
    puts var
    end
    if korisnik.role_id.equal?(2)
      kurs_id = CourseDepartment.find_by_course_id(course_id)
      var = UserEnrollment.where("user_id=? and course_department_id=?",user_id, kurs_id).exists?
      puts var
    end
    if korisnik.role_id.equal?(1)
      puts korisnik.role_id
      var = true
    end
    return var
  end

  def responsible?(course_id)
    var = Course.find(course_id)
    odgovorni= var.responsible
    korisnik = User.find(odgovorni)
    return korisnik.name
  end
end
