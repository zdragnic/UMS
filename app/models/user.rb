class User < ApplicationRecord
  belongs_to :role

  def self.authenticate(username="", login_password="")
    user = User.find_by_username(username)
    pass= User.find_by_password(login_password)
    if user && pass
      return user
    else
      return false
    end
  end

end
