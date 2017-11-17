class SessionsController < ApplicationController
  def new
  end

  def create
    user = User.authenticate(params[:session][:username],params[:session][:password])
    if user
      # Log the user in and redirect to the user's show page.
      log_in user
      redirect_to user
    elsif (params[:session][:username]== '' || params[:session][:password]== '') || (params[:session][:username]== '' && params[:session][:password]== '')
      # ovaj flash ima jos i :success :info, i sam ce tamo u izgledu promijeniti boje diva u zelenu ili plavu
      flash.now[:danger] = 'Unesite username i password'
      render 'new'
    else
      flash.now[:danger] = 'Pogresna username/password kombinacija'
      render 'new'
    end
  end


  def destroy
    log_out
    redirect_to root_url
  end
end
