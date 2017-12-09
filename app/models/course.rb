class Course < ApplicationRecord
  belongs_to :user
  attr_accessor :responsible
end
