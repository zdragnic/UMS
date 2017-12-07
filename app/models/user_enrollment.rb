class UserEnrollment < ApplicationRecord
  belongs_to :study_year
  belongs_to :user
  belongs_to :course_department
  belongs_to :lab_group
end
