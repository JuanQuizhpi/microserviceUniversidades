package ec.edu.ups.ppw64.demo64.services;


import java.util.List;

import config.ConfigJaeger;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import ec.edu.ups.ppw64.demo64.business.GestionUniversidades;
import ec.edu.ups.ppw64.demo64.model.Universidad;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("universidades")
public class UniversidadServices {
	
private final Tracer tracer = GlobalTracer.get();
	
	@Inject
	private GestionUniversidades gUniversidades;
	
	@Inject
	private ConfigJaeger configjaeger;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response guardarUniversidad(Universidad universidad) {
		Span spanGuardarUniversidad = tracer.buildSpan("Creacion Universidades").start();
		System.out.println(universidad);
		try{
			gUniversidades.guardarUniversidades(universidad);
			ErrorMessage error = new ErrorMessage(1, "OK");
			return Response.status(Response.Status.CREATED)
					.entity(error)
					.build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(error)
					.build();
		} finally {
			spanGuardarUniversidad.finish();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizarUniversidad(Universidad universidad) {
		Span spanActualizarUniversidad = tracer.buildSpan("Actualizacion de universidades").start();
		try{
			gUniversidades.actualizarUniversidad(universidad);
			return Response.ok(universidad).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}finally {
			spanActualizarUniversidad.finish();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarUniversidad(@QueryParam("id") String id) {
		Span spanEliminarUniversidad = tracer.buildSpan("Eliminar Universidad").start();
		try{
			this.gUniversidades.borrarUniversidad(id);
			ErrorMessage error = new ErrorMessage(1, "OK");
			return Response.status(Response.Status.CREATED)
					.entity(error)
					.build();
		}catch (Exception e) {
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(error)
					.build();
		} finally {
			spanEliminarUniversidad.finish();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUniversidadPorID(@QueryParam("id") String id) {
		Span spanGetUniversidadPorID = tracer.buildSpan("Universidad por ID").start();
		try{
			System.out.println("id" +  id);
			Universidad universidad = gUniversidades.getUniversidadPorID(id);
			return Response.ok(universidad).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(4, "No hay universidad");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}finally {
			spanGetUniversidadPorID.finish();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getUniversidades(){
		Span spanGetUniversidades = tracer.buildSpan("Obtener Universidades").start();
		
		try {
			List<Universidad> universidades = gUniversidades.getUniversidades();
			if(universidades.size()>0) {
				return Response.ok(universidades).build();
			} else {
				ErrorMessage error = new ErrorMessage(6, "No se registran universidades");
				return Response.status(Response.Status.NOT_FOUND)
						.entity(error)
						.build();
			}
		} catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(6, "No se registran universidades");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		} finally {
			spanGetUniversidades.finish();
		}

	}

}
