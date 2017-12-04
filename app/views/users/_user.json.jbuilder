json.extract! user, :id, :username, :password, :name, :lastname, :birthDate, :address, :birthplace, :role_id, :created_at, :updated_at
json.url user_url(user, format: :json)
