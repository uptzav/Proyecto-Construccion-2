@extends('Shared._Layout')

@section('header')
    <h1>
        Casos Reportados
    </h1>
@endsection

@section('content')
    <div class="row">
        <div class="col-md-12">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">Lista de Casos Reportados</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>TIPO DE CASO</th>
                            <th>APELLIDOS</th>
                            <th>NOMBRES</th>
                            <th>SEXO</th>
                            <th>EDAD</th>
                            <th>CIUDAD</th>
                        </tr>
                        </thead>
                        <tbody>
                        @foreach($casosReportados as $item)
                            <tr>
                                <td>{{$item->tipoCaso}}</td>
                                <td>{{$item->apellidoPaciente}}</td>
                                <td>{{$item->nombrePaciente}}</td>
                                <td>{{$item->sexo}}</td>
                                <td>{{$item->edad}}</td>
                                <td>{{$item->ciudad}}</td>
                            </tr>
                        @endforeach
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
@endsection
