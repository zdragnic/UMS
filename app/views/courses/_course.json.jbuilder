json.extract! course, :id, :title, :code, :user_id, :responsible, :created_at, :updated_at
json.url course_url(course, format: :json)
