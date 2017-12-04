class HomeController < ApplicationController

  def index
  @current_user ||= User.find_by(id: session[:user_id]) if session[:user_id]
    @courses= Course.find_by(user_id: 2)
  end

end
