json.extract! exam_student, :id, :exam_id, :user_id, :status, :created_at, :updated_at
json.url exam_student_url(exam_student, format: :json)
