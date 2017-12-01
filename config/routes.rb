Rails.application.routes.draw do
  get 'home/index'

  get 'welcome/index'
  get 'sessions/new'
  resources :users
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
  root 'welcome#index'
  get    '/login',   to: 'sessions#new'
  post   '/login',   to: 'sessions#create'
  delete '/logout',  to: 'sessions#destroy'

  get 'about' => "welcome#about", as: :about
  get 'index' => "home#index", as: :index


end
