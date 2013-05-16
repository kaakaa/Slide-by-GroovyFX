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

def getPage(anchor){
	return new SceneGraphBuilder().stage(title: 'GroovyFX Hello World', visible: true, ) {
		scene(width: 640, height: 480, root: anchor)
	}
}

public VBox createPage(int pageIndex, textList) {
	VBox box = new VBox(5)
	textList.each { line ->
		VBox element = new VBox()
		Hyperlink link = new Hyperlink("Item ")
		link.setVisited(true)
		Label text = new Label(line + link.getText())
		element.getChildren().addAll(link, text)
		box.getChildren().add(element)
	}
	return box
}

def getAnchorPane(pagination){
	AnchorPane anchor = new AnchorPane()
	AnchorPane.setTopAnchor(pagination, 10.0)
	AnchorPane.setRightAnchor(pagination, 10.0)
	AnchorPane.setBottomAnchor(pagination, 10.0)
	AnchorPane.setLeftAnchor(pagination, 10.0)
	String image = getClass().getClassLoader().getResource("hellofx/20130504shugo_2.jpg").toExternalForm()
	anchor.setStyle("-fx-background-image: url('" + image + "');")
	anchor.getChildren().addAll(pagination)
	return anchor
}

def getPagination(){
	pagination = new Pagination(28, 0)
	pagination.setStyle("-fx-border-color:red;")
	pagination.setPageFactory(new Callback<Integer, Node>() {
		@Override
		public Node call(Integer pageIndex) {
			return createPage(pageIndex, ['hogehoge','fugafuga'])
		}
	})
	return pagination
}
