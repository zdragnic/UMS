class Course < ApplicationRecord
  belongs_to :user
  attr_accessor :title, :code, :user_id
end
