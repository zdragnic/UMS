class CourseDepartment < ApplicationRecord
  belongs_to :course
  belongs_to :department
  belongs_to :academicyear
  belongs_to :semester
end
