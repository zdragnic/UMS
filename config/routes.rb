Rails.application.routes.draw do

  resources :notices
  resources :user_enrollments
  resources :student_details
  resources :employee_details

  #nested-resources


  resources :users do
    resources :courses
  end


  resources :users
  get 'home/index'

  get 'welcome/index'
  get 'sessions/new'
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
  root 'welcome#index'
  get    '/login',   to: 'sessions#new'
  post   '/login',   to: 'sessions#create'
  delete '/logout',  to: 'sessions#destroy'

  get 'about' => "welcome#about", as: :about
  get 'index' => "home#index", as: :index

  # da mozemo sve kurseve ispisati neovisno od usera
  get '/courses' , to: 'courses#kursevi'

end