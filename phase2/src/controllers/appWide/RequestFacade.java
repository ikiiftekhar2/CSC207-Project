package controllers.appWide;
import controllers.appWide.IMenuAdapter;
import presenters.Presenter;

import java.util.ArrayList;


public class RequestFacade {
    /**
     * an adapter responsible for adapting this class with a presenter that prints menus.
     */
    IMenuAdapter menuAdapter = new MenuAdapter();
    /**
     * an array of RequestControllers that can be called by the request menu of the request facade
     */
    protected RequestController[] requestControllers;
    /**
     * a requester which calls the facade
     */
    protected String requester;
    /**
     * a string representing the menu of requests
     */
    protected String requests;
    /**
     * a presenter object responsible for printing messages to CLI
     */
    protected Presenter presenter = new Presenter();
    /**
     * Constructor for a request facade responsible for reading user input for the request they would like to perform.
     *
     * @param requestControllers an array of RequestControllers that can be called by the request menu of the
     *                           request facade
     */
    public RequestFacade(RequestController[] requestControllers) {
        this.requestControllers = requestControllers;
        buildRequests();
    }

    /**
     * Validates user input for the request they wish to perform. If the request is valid, the appropriate controller
     * is called. Otherwise, an error message is printed to the user, and they are asked to choose a request again.
     */
    protected void handleRequest(String request) {
        try {
            int requestNumber = Integer.parseInt(request);
            if (requestNumber < 0 || requestNumber >= requestControllers.length) {
                presenter.blockPrint("The request entered is invalid. ");
            } else if (requestControllers[requestNumber].handleRequest(requester)) {
                return;
            }
        } catch (NumberFormatException e) {
            presenter.blockPrint("The request entered is invalid. ");
        }
        presentRequest();
    }

    /**
     * Presents a menu of requests where the user can pick a request by inputting a number.
     */
    public void presentRequest() {
        String request = menuAdapter.printMenu(requests);
        handleRequest(request);
    }

    /**
     * Sets the requester of the request facade.
     */
    public void setRequester(String requester) {
        this.requester = requester;
    }

    /**
     * Builds a string representing a menu of requests that will be presented to the user.
     */
    protected void buildRequests() {
        requests = menuAdapter.buildMenu(requestControllers);
    }
}


