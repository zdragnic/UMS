class HomeController < ApplicationController

  def index
  @current_user ||= User.find_by(id: session[:user_id]) if session[:user_id]
  @courses = Course.where("user_id=?",session[:user_id])
  end

end
