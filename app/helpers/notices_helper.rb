module NoticesHelper
  def stavioObavijest?(user_id, notice_id)
    korisnik= User.find(user_id)

    var = Notice.where("user_id=? and id=?",user_id, notice_id).exists?
    puts var
    if korisnik.role_id.equal?(1)
      puts korisnik.role_id
      var = true
    end
    return var
  end
end
