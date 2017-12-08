module HomeHelper

  def slusaKurs?(user_id,course_id)
   var= Course.where("user_id=? and id=?",user_id, course_id).exists?
    puts var
    return var
  end
end
