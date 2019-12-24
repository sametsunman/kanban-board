<template>
  <div id="app">
    <b-container class="p-3">
      <b-row>
        <div class="col-sm-4">
          <div class="panel-heading">
            <h3>
              <i class="fas fa-list"></i> TODO
            </h3>
          </div>
          <div class="panel-body">
            <div id="TODO" class="kanban-centered">
              <b-card
                class="mb-3"
                draggable="true"
                v-for="task of this.getTaskDataByType('TODO')"
                :key="task.id"
                :id="task.id"
                @mouseover="selectTask(task.id)"
              >
                <b-card-body class="card-body">
                  <div class="card-title">
                    <h5>{{task.name}}</h5>
                  </div>
                  <div class="card-text">
                    <p>{{task.description}}</p>
                    <b style="font-size: x-small;">Due Date: {{task.dueDate}}</b>
                  </div>
                  <b-row>
                    <button
                      class="btn btn-outline-danger btn-sm ml-auto"
                      style="height:22px;padding:1px 5px;fontSize:12px"
                      @click="deleteTask(task.id)"
                    >
                      <i class="fas fa-times"></i>
                    </button>
                  </b-row>
                </b-card-body>
              </b-card>
              <div id="todoDefault" class="p-5" />
            </div>
          </div>
        </div>
        <div class="col-sm-4">
          <div class="panel-heading">
            <h3>
              <i class="fas fa-spinner"></i> ONGOING
            </h3>
          </div>
          <div class="panel-body">
            <div id="ONGOING" class="kanban-centered">
              <b-card
                class="mb-3"
                draggable="true"
                v-for="task of this.getTaskDataByType('ONGOING')"
                :key="task.id"
                :id="task.id"
              >
                <b-card-body class="card-body">
                  <div class="card-title">
                    <h5>{{task.name}}</h5>
                  </div>
                  <div class="card-text">
                    <p>{{task.description}}</p>
                    <b style="font-size: x-small;">Due Date: {{task.dueDate}}</b>
                  </div>
                  <b-row>
                    <button
                      class="btn btn-outline-danger btn-sm ml-auto"
                      style="height:22px;padding:1px 5px;fontSize:12px"
                      @click="deleteTask(task.id)"
                    >
                      <i class="fas fa-times"></i>
                    </button>
                  </b-row>
                </b-card-body>
              </b-card>
              <div id="ongoingDefault" class="p-5" />
            </div>
          </div>
        </div>
        <div class="col-sm-4">
          <div class="panel-heading">
            <h3>
              <i class="fas fa-check-circle"></i> DONE
            </h3>
          </div>
          <div class="panel-body">
            <div id="DONE" class="kanban-centered">
              <div
                class="card mb-3"
                draggable="true"
                v-for="task of this.getTaskDataByType('DONE')"
                :key="task.id"
                :id="task.id"
              >
                <span class="w-50 badge badge-success ml-auto">Completed on: {{task.completedDate}}</span>
                <b-card-body>
                  <div class="card-title">
                    <h5>{{task.name}}</h5>
                  </div>
                  <div class="card-text">
                    <p>{{task.description}}</p>
                    <b style="font-size: x-small;">Due Date: {{task.dueDate}}</b>
                  </div>
                  <b-row>
                    <button
                      class="btn btn-outline-danger btn-sm ml-auto"
                      style="height:22px;padding:1px 5px;fontSize:12px"
                      @click="deleteTask(task.id)"
                    >
                      <i class="fas fa-times"></i>
                    </button>
                  </b-row>
                </b-card-body>
              </div>
              <div id="doneDefault" class="p-5" />
            </div>
          </div>
        </div>
      </b-row>
    </b-container>
    <button
      type="button"
      class="btn btn-dark btn-fab rounded-circle pt-2 pb-0"
      data-toggle="modal"
      data-target="#popup"
    >
      <h4 class="fas fa-plus"></h4>
    </button>

    <div class="modal fade" id="popup">
      <div class="modal-dialog">
        <div class="modal-content">
          <form>
            <div class="modal-header">
              <h4 class="modal-title">Create A Task</h4>
              <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <div class="modal-body">
              <div class="form-group">
                <label for="title">TITLE:</label>
                <input
                  v-model="newTask.name"
                  class="form-control"
                  id="name"
                  placeholder="Task Title"
                  name="name"
                />
              </div>
              <div class="form-group">
                <label for="description">DESCRIPTION:</label>
                <textarea
                  v-model="newTask.description"
                  class="form-control"
                  rows="4"
                  id="description"
                  name="descr"
                ></textarea>
              </div>
              <div class="form-group">
                <label for="date">DATE:</label>
                <input
                  v-model="newTask.dueDate"
                  type="date"
                  class="form-control"
                  id="date"
                  placeholder="dd/mm/yyyy"
                  name="date"
                />
              </div>
            </div>

            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
              <button
                type="button"
                class="btn btn-primary"
                data-dismiss="modal"
                @click="sendNewKanbanTask()"
              >OK</button>
            </div>
          </form>
        </div>
      </div>
    </div>

    <div class="modal modal-static fade" id="processing-modal" role="dialog" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-body">
            <div class="text-center">
              <i class="fa fa-refresh fa-5x fa-spin"></i>
              <h4>Processing...</h4>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      kanbanData: [],
      newTask: {},
      selectedTaskId: ""
    };
  },
  mounted() {
    this.fetchKanbanData();
    this.kanbanCol();
  },
  methods: {
    fetchKanbanData() {
      var vm = this;
      axios({
        method: "get",
        url: "http://localhost:8088/api/v1/tasks/"
      })
        .then(res => {
          vm.kanbanData = res.data.results;
        })
        .catch(function(error) {
          console.log(error.message);
        });
    },
    sendNewKanbanTask() {
      var vm = this;
      axios({
        method: "post",
        data: vm.newTask,
        url: "http://localhost:8088/api/v1/tasks/"
      })
        .then(res => {
          if (res.data.code != "Error") {

            vm.newTask = {};
            vm.fetchKanbanData();
            alert("New task added successfully");
          } else {
            alert("Please check the form");
          }
        })
        .catch(function(error) {
          console.log(error.message);
        });
    },
    changeTaskType(taskType) {
      var vm = this;
      var selectedTask = this.kanbanData.find(
        x => x.id === this.selectedTaskId
      );
      selectedTask.process = taskType;

      axios({
        method: "put",
        data: selectedTask,
        url: "http://localhost:8088/api/v1/tasks/" + vm.selectedTaskId
      })
        .then(res => {
          if (res.data.code != "Error") {
            vm.fetchKanbanData();
          } else {
            alert("Something get wrong");
          }
        })
        .catch(function(error) {
          console.log(error.message);
        });
    },
    deleteTask(id) {
      if (confirm("Are you sure to delete?")) {
        var vm = this;
        axios({
          method: "delete",
          url: "http://localhost:8088/api/v1/tasks/" + id
        })
          .then(res => {
            if (res.data.code != "Error") {
              vm.fetchKanbanData();
              alert("The task deleted successfully");
            } else {
              alert("Something get wrong");
            }
          })
          .catch(function(error) {
            console.log(error.message);
          });
      }
    },
    selectTask(id) {
      this.selectedTaskId = id;
    },
    getTaskDataByType(taskType) {
      return this.kanbanData.filter(x => x.process === taskType);
    },
    kanbanCol() {
      var kanbanCol = $(".panel-body");
      kanbanCol.css("max-height", window.innerHeight - 150 + "px");

      var kanbanColCount = parseInt(kanbanCol.length);
      $(".container-fluid").css("min-width", kanbanColCount * 350 + "px");

      this.draggableInit();

      $(".panel-heading").click(function() {
        var $panelBody = $(this)
          .parent()
          .children(".panel-body");
        $panelBody.slideToggle();
      });
    },
    draggableInit() {
      var sourceId;
      var vm = this;

      $("[draggable=true]").bind("dragstart", function(event) {
        sourceId = $(this)
          .parent()
          .attr("id");

        event.originalEvent.dataTransfer.setData(
          "text/plain",
          event.target.getAttribute("id")
        );
      });

      $(".panel-body").bind("dragover", function(event) {
        event.preventDefault();
      });

      $(".panel-body").bind("drop", function(event) {
        var children = $(this).children();
        var targetId = children.attr("id");

        if (sourceId != targetId) {
          var elementId = event.originalEvent.dataTransfer.getData(
            "text/plain"
          );

          $("#processing-modal").modal("toggle");

          setTimeout(function() {
            vm.changeTaskType(targetId);

            var element = document.getElementById(elementId);
            children.prepend(element);
            $("#processing-modal").modal("toggle");
          }, 500);
        }

        event.preventDefault();
      });
    }
  }
};
</script>

<style>
.btn-fab {
  position: fixed;
  right: 40px;
  bottom: 40px;
}
</style>
