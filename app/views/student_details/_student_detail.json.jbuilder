json.extract! student_detail, :id, :user_id, :indeks, :budget, :enrollmentDate, :disrollmentDate, :graduationDate, :created_at, :updated_at
json.url student_detail_url(student_detail, format: :json)
