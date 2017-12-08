module CoursesHelper
  def drziKurs?(user_id, course_id)
    korisnik= User.find(user_id)
    if korisnik.role_id.equal?(4)
    var = Course.where("responsible=? and id=?",user_id, course_id).exists?
    puts var
    end
    if korisnik.role_id.equal?(2)
      var = Course.where("user_id=? and id=?",user_id, course_id).exists?
    end
    if korisnik.role_id.equal?(1)
      puts korisnik.role_id
      var = true
    end
    return var
  end

  def responsible(course_id)
    var = Course.where("id=?",course_id)
    odgovorni= var.responsible
    korisnik = User.where("id=?", odgovorni)
    return korisnik.name
  end
end
