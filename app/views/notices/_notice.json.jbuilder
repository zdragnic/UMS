json.extract! notice, :id, :title, :text, :user_id, :course_id, :created_at, :updated_at
json.url notice_url(notice, format: :json)
