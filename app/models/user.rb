class User < ApplicationRecord
  belongs_to :role
  has_one :student_detail
  has_one :employee_detail
  has_many :courses
  attr_accessor :name

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
