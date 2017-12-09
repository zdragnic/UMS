json.extract! request, :id, :status, :user_id, :credential_id, :created_at, :updated_at
json.url request_url(request, format: :json)
