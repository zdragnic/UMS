module RequestsHelper

  def rola?(user_id)
    var = false
    user = User.find(user_id)
    if user.role_id.equal?(3)
      var = true
    end
    puts "DA li je studentska?"
    puts var
    return var
  end

def StudentZahtjevi(user_id, request_id)
  user = User.find(user_id)
  var=true
if user.role_id.equal?(2)
  var = Request.where("user_id=? and id=?",user_id, request_id).exists?
end
  return var
end

  def status(value)
    zahtjev = Request.find(value)
    l = Request.where(["id = ?", value])

    puts "STATUS"
   puts l.pluck(:status)[0]

    x="Obrađeno"
    if l.pluck(:status)[0].equal?(0)
      x="Neobrađeno"
    end
    return x

      end

  end

