package hellofx

import static groovyx.javafx.GroovyFX.start
import groovyx.javafx.SceneGraphBuilder
import javafx.scene.layout.VBox
import javafx.scene.control.Label
import javafx.scene.control.Hyperlink
import javafx.scene.Node
import javafx.scene.control.Pagination
import javafx.util.Callback
import javafx.scene.layout.AnchorPane
import javafx.scene.text.*
import javafx.scene.paint.*

start {

    def pagination = getPagination()
    def anchor = getAnchorPane(pagination)
    getPage(anchor)
}

def getAnchorPane(pagination){
    AnchorPane anchor = new AnchorPane()
    AnchorPane.setTopAnchor(pagination, 10.0)
    AnchorPane.setRightAnchor(pagination, 10.0)
    AnchorPane.setBottomAnchor(pagination, 10.0)
    AnchorPane.setLeftAnchor(pagination, 10.0)
    anchor.getChildren().addAll(pagination)
    return anchor
}

def getPagination(){
    pagination = new Pagination(28, 0)
    pagination.setStyle("-fx-border-color:red;")
    pagination.setPageFactory(new Callback<Integer, Node>() {
        @Override
        public Node call(Integer pageIndex) {
	  if(pageIndex==2){
		return createPage2(pageIndex)
	  }else{
          	return createPage(pageIndex)
	  }
        }
    })
    return pagination
}

def getPage(anchor){
    return new SceneGraphBuilder().stage(title: 'GroovyFX Hello World', visible: true, ) {
        scene(width: 640, height: 480, root: anchor) {
            hbox(padding: 60) {
                text(text: 'Groovy', font: '80pt sanserif') {
                    fill linearGradient(endX: 0, stops: [PALEGREEN, SEAGREEN])
                }
                text(text: 'FX', font: '80pt sanserif') {
                    fill linearGradient(endX: 0, stops: [CYAN, DODGERBLUE])
                    effect dropShadow(color: DODGERBLUE, radius: 25, spread: 0.25)
                }
            }
            rotate(angle: 90, axis: [1,1,0])
            imageView(viewport: [0,0, 400,400])
        }
    }
}

public int itemsPerPage() {
    return 8;
}

public VBox createPage2(int pageIndex) {
    VBox element = new VBox()
    Hyperlink link = new Hyperlink("http://google.com")
    link.setVisited(true)
    Label text = new Label("Google -> " + link.getText())
    element.getChildren().addAll(link, text)
    return element
}

public VBox createPage(int pageIndex) {
    VBox box = new VBox(5)
    int page = pageIndex * itemsPerPage()
    (page..page+itemsPerPage()).each { i ->
        VBox element = new VBox()
        Hyperlink link = new Hyperlink("Item " + (i+1))
        link.setVisited(true)
        Label text = new Label("Search results\nfor " + link.getText())
        element.getChildren().addAll(link, text)
        box.getChildren().add(element)
    }
    return box
}
